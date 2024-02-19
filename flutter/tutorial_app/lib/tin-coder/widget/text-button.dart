
import 'package:flutter/material.dart';

class MyTextButton extends StatelessWidget {
  const MyTextButton({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(left: 30, top: 20),
      child: TextButton.icon(
          onPressed: () {

          },
          style: TextButton.styleFrom(
            foregroundColor: Colors.white, // color text
            backgroundColor: Colors.lightGreen, // color button
              // minimumSize: const Size(200, 80) // size of button
            padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 50),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15) // border radius
            ),
            elevation: 20, // size shadow
            shadowColor: Colors.black54.withOpacity(1),
            side: const BorderSide(width: 1, color: Colors.black54), // size border
          ),
          icon: const Icon(Icons.cached_outlined),
          label: const Text(
            'Click me',
            style: TextStyle(fontSize: 18),
          ),
          // child: const Text(
          //   'Click me',
          //   style: TextStyle(fontSize: 18),
          // )
      ),
    );
  }
}
