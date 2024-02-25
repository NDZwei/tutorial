import 'package:flutter/material.dart';
import 'package:food_delivery/pages/home/main_food_page.dart';

void main() {
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
      home: MainFoodPage(),
    );
  }
}