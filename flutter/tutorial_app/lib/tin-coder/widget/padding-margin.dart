
import 'package:flutter/material.dart';

class MyPaddingMargin extends StatelessWidget {
  const MyPaddingMargin({super.key});

  @override
  Widget build(BuildContext context) {
    return const Card(
      color: Colors.lightGreen,
      margin: EdgeInsets.only(left: 10, top: 20),
      child: Padding(
        // padding: EdgeInsets.all(8.0), // padding deu 4 huong
        // padding: EdgeInsets.only(left: 10, top: 10, right: 10, bottom: 10), // padding tham so truyen vao
        // padding: EdgeInsets.symmetric(vertical: 10, horizontal: 20), // vertical truc y, horizontal truc x
        padding: EdgeInsets.fromLTRB(10, 10, 10, 10), // left, top, right, bottom
        child: Text('Hello world',
          style: TextStyle(
              fontSize: 16),
        ),
      ),

    );
  }
}
