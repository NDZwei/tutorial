
import 'package:flutter/material.dart';

class MySizedBox extends StatelessWidget {
  const MySizedBox({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: SizedBox.expand(
        child: ElevatedButton(
            onPressed: (){

            },
            style: ElevatedButton.styleFrom(
              foregroundColor: Colors.white,
              backgroundColor: Colors.lightGreen
            ),
            child: const Text('Hello world',
              style: TextStyle(fontSize: 24),)
        )
      ),
    );
  }
}
