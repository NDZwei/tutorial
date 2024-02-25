import 'package:flutter/material.dart';
import 'package:food_delivery/pages/food/recommend_food_detail.dart';
import 'helper/dependencies.dart' as dep;

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await dep.init();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Food delivery',
      theme: ThemeData(
        primaryColor: Colors.blue
      ),
      home: RecommendFoodDetail(),
    );
  }
}