

import 'package:flutter/material.dart';

class MyStack extends StatelessWidget {
  const MyStack({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.grey,
      width: 500,
      height: 500,
      child: Stack(
        // phần tử khai báo ở sau đè lên phần tử trước
        // phần tử nào mà sử dụng set vị trí thì không theo stack
        //
        fit: StackFit.loose, // phần tử cuối cùng wrap container
        alignment: Alignment.center,
        clipBehavior: Clip.none, // dùng để xem phần tử nếu có kích thước tràn ra khỏi stack
        // textDirection: TextDirection.rtl,
        children: [
          Container(
            color: Colors.lightGreen,
            width: 300,
            height: 300,
          ),
          Positioned(
            left: 10,
            bottom: 10,
            child: Container(
              color: Colors.yellowAccent,
              width: 200,
              height: 200,
            ),
          ),
          Align(
            alignment: Alignment.bottomRight,
            child: Container(
              color: Colors.orangeAccent,
              width: 100,
              height: 100,
            ),
          ),
        ],
      ),
    );
  }
}
