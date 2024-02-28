import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';
import 'package:river_pod_course_app/common/widgets/text_custom.dart';

Widget nextButton({
  index = 1,
  Color color = AppColors.primaryElement,
  double borderRadius = 15,
  double spreadRadius = 1,
  double blurRadius = 2,
  required PageController controller
}) {
  return GestureDetector(
    onTap: () {
      if(index < 3) {
        controller.animateToPage(
            index,
            duration: const Duration(milliseconds: 300),
            curve: Curves.easeInOut);
      }
    },
    child: Container(
        width: 325,
        height: 50,
        margin: const EdgeInsets.only(top: 100, left: 25, right: 25),
        decoration: BoxDecoration(
            color: color,
            borderRadius: BorderRadius.circular(borderRadius),
            boxShadow: [
              BoxShadow(
                  color: Colors.grey.withOpacity(0.1),
                  spreadRadius: 1,
                  blurRadius: 2,
                  offset: const Offset(0, 1))
            ]
        ),
        child: Center(
            child: textFontNormal(text: "Next", color: AppColors.primaryBackground)
        ),
    ),
  );
 }
