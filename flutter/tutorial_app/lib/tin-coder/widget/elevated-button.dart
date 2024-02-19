
import 'package:flutter/material.dart';

class MyElevatedButton extends StatelessWidget {
  const MyElevatedButton({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(left: 30, top: 20),
      child: ElevatedButton(
          onPressed: () {},
          style: ElevatedButton.styleFrom(
            foregroundColor: Colors.white,
            backgroundColor: Colors.lightGreen,
            padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 50),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15) // border radius
            ),
            elevation: 20, // size shadow
            shadowColor: Colors.black54.withOpacity(1),
            side: const BorderSide(width: 1, color: Colors.black54),
          ),
          child: const Text(
            'Click me',
            style: TextStyle(
              fontSize: 20
            ),
          ),
      ),
    );
  }
}
