import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';

Widget textCustom({
  String text = "",
  double fontSize = 16,
  Color color = AppColors.primaryThreeElementText,
  bool isCenter = true
}) {
  return Text(
    text,
    textAlign: isCenter ? TextAlign.center : TextAlign.start,
    style: TextStyle(
        color: color,
        fontSize: fontSize,
        fontWeight: FontWeight.normal),
  );
}

Widget textUnderLine({
  String text = "",
  double fontSize = 14,
}) {
  return GestureDetector(
    onTap: () {

    },
    child: Text(
      text,
      style: TextStyle(
        fontWeight: FontWeight.normal,
        fontSize: fontSize,
        color: AppColors.primaryText,
        decoration: TextDecoration.underline,
        decorationColor: AppColors.primaryText,
      ),
    ),
  );
}
