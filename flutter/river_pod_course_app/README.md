# river_pod_course_app

Course app using riverpod 2.0
--- Lệnh cập nhật lại sau khi sửa đổi version ---
    flutter clean
    flutter run
--- Lệnh cập nhật/generate state code ---
    flutter pub run build_runner watch --delete-conflicting-outputs 
--- Install && dd firebase to flutter ---
	install global: npm install -g firebase-tools/npm install -g firebase-tools@{version}
	add to flutter: dart pub global activate flutterfire_cli
	add to path in enviroment pc: C:\Users\YOUR_USER\AppData\Local\Pub\Cache\bin
	check install successfull: flutterfire --version
    start ms-settings:developers
	Thực hiện đăng nhập
--- firebase ---
    cài đặt firebase phù hợp với node version 16:
        npm install -g firebase-tools@9.16.6
    firebase logout
    firebase login
    flutterfire configue
    flutter pub add firebase_core
        
