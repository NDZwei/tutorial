
import 'package:flutter/material.dart';

class MyExpanded extends StatelessWidget {
  const MyExpanded({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        children: [
          Expanded(
            flex: 1, // chia ti le
              child: Container(
                color: Colors.lightGreen,
                height: 150,
            )
          ),
          const SizedBox(width: 10),
          Expanded(
            flex: 2,
              child: Container(
                color: Colors.orangeAccent,
                height: 150,
              )
          ),
          const SizedBox(width: 10),
          Expanded(
            flex: 1,
              child: Container(
                color: Colors.yellowAccent,
                height: 150,
              )
          ),
        ],
      ),
    );
  }
}
