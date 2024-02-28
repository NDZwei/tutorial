
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';
import 'package:river_pod_course_app/common/widgets/text_custom.dart';

import 'button_custom.dart';

Widget appOnboardingPage({
  index = 0,
  String imagePath = "",
  String title = "",
  String subtitle = "",
  required PageController controller,
}) {
  return Column(
    children: [
      Image.asset(imagePath, fit: BoxFit.fitWidth,),
      Container(
          margin: const EdgeInsets.only(top: 15),
          child: textFontNormal(text: title, color: AppColors.primaryText, fontSize: 24)
      ),
      Container(
        margin: const EdgeInsets.only(top: 15),
        padding: const EdgeInsets.only(left: 30, right: 30),
        child: textFontNormal(text: subtitle),
      ),
      nextButton(index: index, controller: controller)
    ],
  );
}
