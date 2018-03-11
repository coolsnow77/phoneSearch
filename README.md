phoneSearch
======
An phoneSearch impl for Android and Java applications(mvp) [手机号查询 android mvp 实现]

# 思路来源
慕课网课程：http://www.imooc.com/comment/776
MVP+OkHttp demo,手机号归属地查询

Dependency
--------
Gradle
```groovy
implementation 'com.squareup.okhttp3:okhttp:3.10.0'
compile 'com.google.code.gson:gson:2.8.2'
compile 'com.alibaba:fastjson:1.2.46'
compile 'com.alibaba:fastjson:1.1.68.android'
```

# 效果图
![Alt text](https://raw.githubusercontent.com/coolsnow77/phoneSearch/master/Screenshots/phoneSearch.png)

ProGuard
--------

If you are using ProGuard you might need to add the following options:

```
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
```

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: https://square.github.io/okhttp
