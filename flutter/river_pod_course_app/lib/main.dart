import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/common/widgets/app_theme.dart';
import 'package:river_pod_course_app/firebase_options.dart';
import 'package:river_pod_course_app/pages/sign_in/sign_in.dart';
import 'package:river_pod_course_app/pages/sign_up/sign_up.dart';
import 'package:river_pod_course_app/pages/welcome/welcome.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(const ProviderScope(child: MyApp()));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme.appThemeData,
      routes: {
        "/": (context) => Welcome(),
        "/signIn": (context) => const SignIn(),
        "/register": (context) => const SignUp(),
      },
    );
  }
}

final appCount = StateProvider<int>((ref) {
  return 0;
});

class MyHomePage extends ConsumerWidget {
  const MyHomePage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    int count = ref.watch(appCount);
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text('First Page'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                count.toString(),
                style: Theme.of(context).textTheme.headlineMedium,
              ),
            ],
          ),
        ),
        floatingActionButton: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            FloatingActionButton(
              onPressed: () {
                Navigator.of(context).pushReplacement(MaterialPageRoute(
                    builder: (BuildContext context) => const SecondPage()));
              },
              tooltip: 'Increment',
              child: const Icon(Icons.arrow_right_outlined),
            ),
            FloatingActionButton(
              onPressed: () {
                ref.read(appCount.notifier).state++;
              },
              tooltip: 'Increment',
              child: const Icon(Icons.add),
            ),
          ],
        ) // This trailing comma makes auto-formatting nicer for build methods.
        );
  }
}

class SecondPage extends ConsumerWidget {
  const SecondPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    int count = ref.watch(appCount);
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text('Second Page'),
        ),
        body: Center(
          child: Container(
            child: Text(
              "$count",
              style: Theme.of(context).textTheme.headlineMedium,
            ),
          ),
        ),
        floatingActionButton: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            FloatingActionButton(
              heroTag: 'one',
              onPressed: () {
                Navigator.of(context).pushReplacement(MaterialPageRoute(
                    builder: (BuildContext context) => const MyHomePage()));
              },
              tooltip: 'Increment',
              child: const Icon(Icons.arrow_left_outlined),
            ),
            FloatingActionButton(
              heroTag: 'two',
              onPressed: () {
                ref.read(appCount.notifier).state--;
              },
              tooltip: 'Increment',
              child: const Icon(Icons.remove),
            ),
          ],
        ));
  }
}
