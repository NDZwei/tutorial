import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';
import 'package:river_pod_course_app/common/widgets/sign_in_widgets.dart';
import 'package:river_pod_course_app/common/widgets/text_custom.dart';

Widget nextButton({
  index = 1,
  Color color = AppColors.primaryElement,
  double borderRadius = 15,
  double spreadRadius = 1,
  double blurRadius = 2,
  required BuildContext context,
  required PageController controller
}) {
  return GestureDetector(
    onTap: () {
      if(index < 3) {
        controller.animateToPage(
            index,
            duration: const Duration(milliseconds: 300),
            curve: Curves.easeInOut);
      } else {
        Navigator.pushNamed(context, "/signIn");
      }
    },
    child: Container(
        width: double.infinity,
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
            child: textCustom(text: "Next", color: AppColors.primaryBackground)
        ),
    ),
  );
 }

 Widget appButton({
   String buttonText = "",
   Color color = AppColors.primaryElement,
   double borderRadius = 15,
   double height = 50,
   double width = double.infinity,
   bool isLogin = false,
   BuildContext? context,
}) {
  return GestureDetector(
    onTap: () {
      Navigator.push(
          context!,
          MaterialPageRoute(builder: (context) => Scaffold(
            appBar: buildAppbar(),
            body: Container(
              color: Colors.white,
            ),
            backgroundColor: Colors.white,
          ))
      );
    },
    child: Container(
      child: Center(
        child: textCustom(
            text: buttonText,
            color: isLogin ? AppColors.primaryBackground : AppColors.primaryText,
            fontSize: 16),
      ),
      width: width,
      height: height,
      decoration: BoxDecoration(
          color: isLogin ? AppColors.primaryElement : AppColors.primaryBackground,
          borderRadius: BorderRadius.circular(borderRadius),
          boxShadow: [
            BoxShadow(
                color: Colors.grey.withOpacity(0.3),
                spreadRadius: 1,
                blurRadius: 2,
                offset: const Offset(0, 1))
          ]
      ),
    ),
  );
 }
