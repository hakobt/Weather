package dev.hakob.weather.di;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.di
 */
@Retention(RUNTIME)
@Target({METHOD, FIELD})
@MapKey
public @interface ViewModelKey {

  Class<? extends ViewModel> value();
}