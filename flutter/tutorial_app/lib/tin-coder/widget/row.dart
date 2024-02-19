
import 'package:flutter/material.dart';

class MyRow extends StatelessWidget {
  const MyRow({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.orangeAccent,
      child: Row(
        mainAxisSize: MainAxisSize.max, // wrap theo size cua children
        mainAxisAlignment: MainAxisAlignment.spaceAround, // can chinh cac item: s
        // spaceBetween: cách đều các child nhưng không cách lề
        // spaceAround: cách đều và khoảng cách các child bằng 2 lần khoảng cách tới lề
        // spaceEvenly: cách đều và khoảng cách các lề và child bằng nhau
        crossAxisAlignment: CrossAxisAlignment.center, // căn theo chiều dọc
        children: [
          ElevatedButton(
              onPressed: () {},
              style: ElevatedButton.styleFrom(
                foregroundColor: Colors.white,
                backgroundColor: Colors.lightGreen
              ),
              child: const Text('Button 1', style: TextStyle(fontSize: 16),)
          ),
          Container(
            height: 70,
            child: ElevatedButton(
                onPressed: () {},
                style: ElevatedButton.styleFrom(
                  foregroundColor: Colors.white,
                  backgroundColor: Colors.lightGreen
                ),
                child: const Text('Button 2', style: TextStyle(fontSize: 16),)
            ),
          ),
        ],
      ),
    );
  }
}
