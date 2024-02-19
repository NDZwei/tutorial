
import 'package:flutter/material.dart';

class MyContainer extends StatelessWidget {
  const MyContainer({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      // color: Colors.lightGreen,
      width: 200,
      height: 200,
      // alignment: Alignment.centerLeft,
      padding: const EdgeInsets.only(left: 20),// can phan tu trong container
      margin: const EdgeInsets.only(left: 20, top: 20),
      decoration:  BoxDecoration(
        color: Colors.lightGreen,
        shape: BoxShape.rectangle,
        borderRadius: const BorderRadius.all(Radius.circular(20)),
        border: Border.all(width: 1, color: Colors.black)
      ),
      // child: const Text('Container',
      //     style: TextStyle(fontSize: 20, color: Colors.white)),
    );
  }
}
