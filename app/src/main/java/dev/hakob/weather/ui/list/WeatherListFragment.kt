package dev.hakob.weather.ui.list

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerFragment
import dev.hakob.weather.R
import kotlinx.android.synthetic.main.fragment_weather_list.*
import javax.inject.Inject

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.ui
 */
class WeatherListFragment : DaggerFragment() {

    private lateinit var addCityDialog: AlertDialog

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: WeatherListViewModel

    private val locationRequestCode = 42

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        createAddCityDialog(container)
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    private fun createAddCityDialog(parent: ViewGroup?) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_city, parent, false)
        alertDialogBuilder.setView(dialogView)
        val okButton = dialogView.findViewById<Button>(R.id.positiveButton)
        val closeButton = dialogView.findViewById<Button>(R.id.negativeButton)

        val editText = dialogView.findViewById<EditText>(R.id.addCityEditText)

        okButton.setOnClickListener {
            if (editText.text.isNotEmpty()) {
                viewModel.addCity(editText.text.toString())
                addCityDialog.dismiss()
            }
        }

        closeButton.setOnClickListener {
            addCityDialog.dismiss()
        }

        addCityDialog = alertDialogBuilder.create()
    }

    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationRequestCode)
        } else {
            viewModel.onLocationPermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == locationRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.onLocationPermissionGranted()
            } else {
                viewModel.onLocationPermissionDenied()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.addCityButton).setOnClickListener {
            addCityDialog.show()
        }

        val listAdapter = WeatherListAdapter {
            val action = WeatherListFragmentDirections.actionOpenForecast(it.cityId)
            findNavController().navigate(action)
        }

        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        listView.adapter = listAdapter
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteCity(viewModel.weatherList.value?.get(viewHolder.adapterPosition)?.cityId)
            }
        })
        itemTouchHelper.attachToRecyclerView(listView)
        listView.layoutManager = layoutManager
        listView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        viewModel = ViewModelProviders.of(this, viewModelFactory)[WeatherListViewModel::class.java]

        viewModel.weatherList.observe(this, Observer(listAdapter::submitList))
    }
}