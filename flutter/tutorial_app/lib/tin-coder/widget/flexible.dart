import 'package:flutter/material.dart';

class MyFlexible extends StatelessWidget {
  const MyFlexible({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Flexible(
          flex: 1,
          fit: FlexFit.loose,// custom size
          child: Container(
            height: 100,
            color: Colors.lightGreen,
          )
        ),
        Flexible(
          flex: 2,
          fit: FlexFit.tight,
          child: Container(
            height: 100, // khong ap dung
            color: Colors.limeAccent,
          )
        ),
        Flexible(
          flex: 1,
          fit: FlexFit.tight,
          child: Container(
          color: Colors.orangeAccent,
          )
        ),
      ],
    );
  }
}
