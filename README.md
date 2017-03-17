# iot-mes-app
Oracle IoT Cloud Serviceと連携するためのMEDデバイスダミーアプリケーションサンプル

## 前提
libフォルダに含まれる各jarをmavenのローカルリポジトリにインストールする。
~~~~
mvn install:install-file -Dfile=lib/device-library.jar -DgroupId=com.oracle.iot -DartifactId=device-library -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=lib/json-20160212.jar -DgroupId=com.oracle.iot -DartifactId=json-20160212 -Dversion=1.0 -Dpackaging=jar
~~~~
