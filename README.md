# Kenin [![Build Status](https://travis-ci.org/kikuchy/kenin.svg?branch=master)](https://travis-ci.org/kikuchy/kenin) [ ![Download](https://api.bintray.com/packages/kikuchy/maven/kenin-java/images/download.svg) ](https://bintray.com/kikuchy/maven/kenin-java/_latestVersion)

Realtime validation framework for Java and Android.

**Note:** This library is before major release. Breaking changes will happen.

![kenin demo](https://raw.githubusercontent.com/kikuchy/kenin/master/kenin_demo.gif)


# Usage

## 1. Add dependencies for your `build.gradle`

```groovy
dependencies {
    compile 'net.kikuchy.kenin:kenin-android:0.0.4' // for android
    compile 'net.kikuchy.kenin.kotlin:kenin-kotlin-android:0.0.4' // for android with Kotlin
    // compile 'net.kikuchy.kenin:kenin-java:0.0.4' // pure java
}
```

## 2. Write validating configuration

### Kotlin DSL

```kotlin
val userId = findViewById(R.id.user_id) as TextInputLayout

// Writing simple,
userId.kenin { requireField() }

// or

// Powerful expression.
userId.kenin {
    requireField() and (alphabet() or numeric())
}
```


### Java

```java
TextInputLayout mUserId = (TextInputLayout) findViewById(R.id.user_id);

// Writing simple,
KeninAndroid.
    create(mUserId, Conditions.requireField());

// or

// Powerful expression.
KeninAndroid.
    create(
        mUserId,
        and(
            Conditions.requireField(),
            or(
                Conditions.alphabet(),
                Conditions.numeric()
            )
        )
    );
```


# Customize

## `Condition`

Form value is checked by the class implements `Condition<V, E>` interface.
Type parameter `V` is type of value will be validated  and `E` is type of error reporting.
You can make original `Condition` for your demand.

```java
KeninAndroid.
    create(mTextInputLayout, new Condition<CharSequence, Integer>() {
        @Override
        public ValidationResult<Integer> validate(CharSequence value) {
            boolean isValid = somethigSpecialValidating(value);
            List<Integer> errors = new ArrayList<>();
            if (!isValid)
                errors.add(R.string.validation_failed);
            return new ValidationResult(isValid, errors);
        }
    });
```

Results of validation is represented `ValidationResult` class. It contains that value is valid or not and validation error reasons.

Error reasons are not only String but also Enum or android resource ID.


## `ResultReciever`

`ResultReciever<E>` interface recieves `ValidationResult` and defines proccessing result.

You can make your own `ResultReciever` and set by the `addResultReciever()` method.

```java
KeninAndroid.
    create(mEditText, Conditions.requireField()).
    addResultReciever(new ResultReciever<String> {
        @Override
        public void validationSucceeded() {
            mSendButton.setEnabled(true);
            mTextView.setText("");
        }
        
        @Override
        public void validationFailed(List<String> errorReasons) {
            mSendButton.setEnabled(false);
            mTextView.setText("Error!");
        }
    });
```


# Concept

## Kenin-Java

**Portable** ... Kenin-Java's goal is the validation library used in anywhere (Swing, AWT, JavaFX, J2ObjC... of cource Android!).

**Pure Java** ... For realizing portability, Kenin-Java have to made with only java code. (We can't run it on JVM if it depends on Android SDK as you know ;) )

**Simple and Minimum** ... Kenin-Java provides only minimum features for validation. And keep simple architecture.


## Kenin-Android

**Battery Included** ... Kenin-Android provides useful classes and methods for validating Android widgets and easy to start using.


## Kenin-Kotlin & Kenin-Kotlin-Android

**Easy to Write and Read** ... The DSL made with Kotlin will help implementing and maintaining your codes.


# Change Log

* 0.0.4
	* Support Kotlin DSL
	* Expands type boundary
* 0.0.3
	* Support Checkbox and add some Conditions
* 0.0.2
	* Support `CompositCondition`, `and()` and `or()`
	* JCenter debut!
* 0.0.1
	* :tada:


# Licence

    Copyright (C) 2016 kikuchy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
