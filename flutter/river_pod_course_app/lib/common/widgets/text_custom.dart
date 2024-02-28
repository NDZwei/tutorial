import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';

Widget textFontNormal({
  String text = "",
  double fontSize = 16,
  Color color = AppColors.primaryThreeElementText
}) {
  return Text(
    text,
    textAlign: TextAlign.center,
    style: TextStyle(
        color: color,
        fontSize: fontSize,
        fontWeight: FontWeight.normal),
  );
}
