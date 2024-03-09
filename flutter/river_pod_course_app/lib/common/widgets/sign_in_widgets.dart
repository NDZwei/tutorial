
import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';
import 'package:river_pod_course_app/common/widgets/text_custom.dart';

AppBar buildAppbar({String text = ""}) {
  return AppBar(
    centerTitle: true,
    title: textCustom(text: text, color: AppColors.primaryText, fontSize: 24),
    bottom: PreferredSize(
      child: Container(
        color: Colors.grey.withOpacity(0.3),
        height: 1,
      ),
      preferredSize: const Size.fromHeight(1),
    ),
  );
}

Widget loginIconButton(String image) {
  return GestureDetector(
    onTap: () {

    },
    child: Container(
      width: 40,
      height: 40,
      child: Image.asset(image, fit: BoxFit.cover,),
    ),
  );
}

Widget thirdPartyLogin() {
  return Container(
    margin: const EdgeInsets.only(left: 80, right: 80, top: 80, bottom: 20),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: [
        loginIconButton("assets/icons/google.png"),
        loginIconButton("assets/icons/apple.png"),
        loginIconButton("assets/icons/facebook.png"),
      ],
    ),
  );
}

Widget imageWidget({String path = "assets/icons/user.png", double width = 16}) {
  return Image.asset(path);
}

Widget textFieldInput({
  String text = "",
  String icon = "",
  double iconWidth = 16,
  double iconHeight = 16,
  String hintText = "",
  bool isPassword = false,
  Color color = AppColors.primaryBackground,
  double radius = 15,
  Color borderColor = AppColors.primaryFourElementText,
  TextEditingController? controller,
  void Function(String value)? func
}) {
  return Container(
    padding: const EdgeInsets.only(left: 25, right: 25),
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        textCustom(text: text),
        const SizedBox(height: 10,),
        Container(
          width: double.infinity,
          height: 60,
          decoration: BoxDecoration(
            color: color,
            borderRadius: BorderRadius.circular(radius),
            border: Border.all(color: borderColor),
          ),
          child: Row(
            children: [
              Container(
                margin: const EdgeInsets.only(left: 17),
                child: Image.asset(icon, width: iconWidth, height: iconHeight,),
              ),
               Container(
                 width: 320,
                 height: 50,
                 child: TextField(
                   controller: controller,
                   onChanged: (value)=>func!(value),
                   keyboardType: TextInputType.multiline,
                   decoration: InputDecoration(
                     hintText: hintText,
                     border: const OutlineInputBorder(
                       borderSide: BorderSide(
                         color: Colors.transparent
                       )
                     ),
                     // default border with input
                     enabledBorder: const OutlineInputBorder(
                         borderSide: BorderSide(
                             color: Colors.transparent
                         )
                     ),
                     // focus input
                     focusedBorder: const OutlineInputBorder(
                         borderSide: BorderSide(
                             color: Colors.transparent
                         )
                     ),
                     disabledBorder: const OutlineInputBorder(
                         borderSide: BorderSide(
                             color: Colors.transparent
                         )
                     )
                   ),
                   maxLines: 1,
                   autofocus: false,
                   obscureText: isPassword,
                 ),
               )
            ],
          ),
        )
      ],
    ),
  );
}

