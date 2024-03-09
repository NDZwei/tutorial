
import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';

class AppTheme {
  static ThemeData appThemeData = ThemeData(
    appBarTheme: const AppBarTheme(
      elevation: 0,
      centerTitle: true,
      backgroundColor: Colors.white,
      iconTheme: IconThemeData(
        color: AppColors.primaryText
      )
    ),
    brightness: Brightness.light,
  );
}