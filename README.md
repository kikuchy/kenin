# Kenin [![Build Status](https://travis-ci.org/kikuchy/kenin.svg?branch=master)](https://travis-ci.org/kikuchy/kenin)

Realtime validation framework for Java and Android.

**Note:** This library is before major release. Breaking changes will happen.

![kenin demo](https://raw.githubusercontent.com/kikuchy/kenin/master/kenin_demo.gif)


# Usage

## 1. Add dependencies for your `build.gradle`

```groovy
dependencies {
    compile 'net.kikuchy.kenin:kenin-android:0.0.2' // for android
    // compile 'net.kikuchy.kenin:kenin-java:0.0.2' // pure java
}
```

## 2. Write validating configuration

```java
TextInputLayout mUserId = (TextInputLayout) findViewById(R.id.user_id);

// Writing simple,
KeninAndroid.
    builder(mUserId).
    setCondition(Conditions.requireField()).
    build();

// or

// Powerful expression.
KeninAndroid.
    builder(mUserId).
    setCondition(
        and(
            Conditions.requireField(),
            or(
                Conditions.alphabet(),
                Conditions.numeric())).
    build();
```


# Customize

## `Condition`

Value is checked by the class implements `Condition<V, E>` interface.
Type parameter `V` is type of value will be validated  and `E` is type of error reporting.
You can make original `Condition` for your demand.

```java
KeninAndroid.
    builder(mUserId).
    setCondition(new Condition<CharSequence, Integer>() {
        @Override
        public ValidationResult<Integer> validate(CharSequence value) {
            boolean isValid = somethigSpecialValidating(value);
            List<ErrorReason<Integer>> errors = new ArrayList<>();
            errors.add(new ErrorReason() {
                @Override
                public Integer getReason() {
                    return R.string.validation_failed;
                }
            });
            return new ValidationResult(isValid, errors);
        }
    }).
    build();
```

Results of validation is represented `ValidationResult` class. It contains that value is valid or not and validation error messages.

`ErrorMessage` represents single validation error message. For future, it will wrap not only String message but also Android Resource IDs or Enums.


# Concept

## Kenin-Java

**Portable** ... Kenin-Java's goal is the validation library used in anywhere (Swing, AWT, JavaFX, J2ObjC... of cource Android!).

**Pure Java** ... For realizing portability, Kenin-Java have to made with only java code. (We can't run it on JVM if it depends on Android SDK as you know ;) )

**Simple and Minimum** ... Kenin-Java provides only minimum features for validation. And keep simple architecture.


## Kenin-Android

**Battery Included** ... Kenin-Android provides useful classes and methods for validating Android widgets and easy to start using.



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
