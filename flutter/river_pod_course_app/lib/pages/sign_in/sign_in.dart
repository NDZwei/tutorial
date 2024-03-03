import 'package:flutter/material.dart';
import 'package:river_pod_course_app/common/widgets/button_custom.dart';
import 'package:river_pod_course_app/common/widgets/sign_in_widgets.dart';
import 'package:river_pod_course_app/common/widgets/text_custom.dart';

class SignIn extends StatelessWidget {
  const SignIn({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      child: SafeArea(
        child: Scaffold(
          appBar: buildAppbar(text: "Login"),
          backgroundColor: Colors.white,
          body: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                thirdPartyLogin(),
                Center(
                    child: textCustom(text: "Or use your email account login")),
                const SizedBox(height: 50,),
                textFieldInput(
                    text: "Email",
                    icon: "assets/icons/user.png",
                    hintText: "Input your email address"),
                const SizedBox(height: 20,),
                textFieldInput(
                    text: "Password",
                    icon: "assets/icons/lock.png",
                    hintText: "******",
                    isPassword: true
                ),
                const SizedBox(height: 20,),
                Container(
                  margin: const EdgeInsets.only(left: 30),
                  child: textUnderLine(text: "For got password?"),
                ),
                const SizedBox(height: 100,),
                Container(
                  margin: const EdgeInsets.symmetric(horizontal: 25),
                  child: appButton(
                    context: context,
                    buttonText: "Login",
                    isLogin: true),
                ),
                const SizedBox(height: 20,),
                Container(
                  margin: const EdgeInsets.symmetric(horizontal: 25),
                  child: appButton(
                    context: context,
                    buttonText: "Register",
                    isLogin: false,
                    func: () {
                      Navigator.pushNamed(context, "/register");
                    }
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}