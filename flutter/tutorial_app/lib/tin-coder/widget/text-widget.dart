
import 'package:flutter/material.dart';

class TextWidget extends StatelessWidget {
  const TextWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return const Text('Mã bạn đưa ra là một phần của một tệp Blade trong Laravel,'
        ' một framework PHP phổ biến được sử dụng để phát triển các ứng dụng web',
      textDirection: TextDirection.ltr, // left to right
      textAlign: TextAlign.justify, // căn lề - đêu 2 bên
      maxLines: 2, // hiển thị tối đa 2 dòng
      overflow: TextOverflow.ellipsis, // vượt quá container ẩn và chuyển thành ...
      style: TextStyle(
        fontSize: 15,
        fontWeight: FontWeight.w400,
        fontFamily: 'Regular',
        color: Colors.blueAccent
      ),
    );
  }
}
