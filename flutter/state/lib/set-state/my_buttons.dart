
import 'package:flutter/material.dart';

class MyButton extends StatelessWidget {
  final String topic;
  final Function callbackFunction;
  const MyButton({super.key, required this.topic, required this.callbackFunction});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        onTap: () {
          callbackFunction(topic);
        },
        child: Container(
          width: double.maxFinite,
          height: 70,
          margin: const EdgeInsets.only(top: 20, left: 20, right: 20),
          decoration: BoxDecoration(
              color: Colors.blue,
              borderRadius: BorderRadius.circular(20)
          ),
          child: Center(
              child: Text(
                topic,
                style: const TextStyle(
                    fontSize: 20,
                    color: Colors.white
                ),
              )
          ),
        )
    );
  }
}
