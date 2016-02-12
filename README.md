# Kenin [![Build Status](https://travis-ci.org/kikuchy/kenin.svg?branch=master)](https://travis-ci.org/kikuchy/kenin)

Realtime validation framework for Java and Android.

**Note:** This library is before major release. Breaking changes will happen.


# Usage

## Simple Usage

(Demonstration GIF here)

```java
TextInputLayout mUserId = (TextInputLayout) findViewById(R.id.user_id);

KeninAndroid.
    builder(mUserId).
    setCondition(Conditions.requireField()).
    build();
```


## `Condition`

Value is checked by the class implements `Condition` interface.
You can make original `Condition` for your demand.

```java
KeninAndroid.
    builder(mUserId).
    setCondition(new Condition<CharSequence>() {
        @Override
        public ValidationResult validate(CharSequence value) {
            boolean isValid = somethigSpecialValidating(value);
            ErrorMessageCollection errors = new ErrorMessageCollection();
            errors.add
            return new ValidationResult(isValid, new ErrorMessage);
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