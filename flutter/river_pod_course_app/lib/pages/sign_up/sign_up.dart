import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/common/app_loader/app_loader.dart';
import 'package:river_pod_course_app/common/controller/sign_up_controller.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';
import 'package:river_pod_course_app/common/widgets/button_custom.dart';
import 'package:river_pod_course_app/common/widgets/sign_in_widgets.dart';
import 'package:river_pod_course_app/common/widgets/text_custom.dart';
import 'package:river_pod_course_app/pages/sign_up/notifier/register_notifier.dart';


class SignUp extends ConsumerStatefulWidget  {
  const SignUp({Key? key}) : super(key: key);

  @override
  _SignUpState createState() => _SignUpState();
}

class _SignUpState extends ConsumerState<SignUp> {
  late SignUpController _controller;

  @override
  void initState() {
    _controller = SignUpController(ref: ref);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final registerProvider = ref.watch(registerNotifierProvider);
    final loader = ref.watch(appLoaderProvider);
    return Container(
      color: Colors.white,
      child: SafeArea(
        child: Scaffold(
          appBar: buildAppbar(text: "Sign up"),
          backgroundColor: Colors.white,
          body: loader == false ?
          SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 30,),
                Center(
                    child: textCustom(text: "Enter your details below & free sign up")
                ),
                const SizedBox(height: 50,),
                textFieldInput(
                  text: "User name",
                  icon: "assets/icons/user.png",
                  hintText: "Enter your user name",
                  func: (value) => ref
                      .read(registerNotifierProvider.notifier)
                      .onUsernameChange(value)
                ),
                const SizedBox(height: 20,),
                textFieldInput(
                  text: "Email",
                  icon: "assets/icons/user.png",
                  hintText: "Enter your email",
                  func: (value) => ref
                      .read(registerNotifierProvider.notifier)
                      .onEmailChange(value)
                ),
                const SizedBox(height: 20,),
                textFieldInput(
                  text: "Password",
                  icon: "assets/icons/lock.png",
                  hintText: "Enter your password",
                  isPassword: true,
                  func: (value) => ref
                      .read(registerNotifierProvider.notifier)
                      .onPasswordChange(value)
                ),
                const SizedBox(height: 20,),
                textFieldInput(
                  text: "Confirm password",
                  icon: "assets/icons/lock.png",
                  hintText: "Enter your confirm password",
                  isPassword: true,
                  func: (value) => ref
                      .read(registerNotifierProvider.notifier)
                      .onConfirmPasswordChange(value)
                ),
                const SizedBox(height: 20,),
                Container(
                  margin: const EdgeInsets.only(left: 30),
                  child: textCustom(
                      text: "By creating an account you have to agree with our them & condition",
                      fontSize: 14,
                      isCenter: false,
                  ),
                ),
                const SizedBox(height: 50,),
                Container(
                  margin: const EdgeInsets.symmetric(horizontal: 25),
                  child: appButton(
                    context: context,
                    buttonText: "Register",
                    isLogin: true,
                    func: () => _controller.handleSignUp()
                  ),
                ),
              ],
            ),
          )
          :
          const Center(
            child: CircularProgressIndicator(
              backgroundColor: Colors.blue,
              color: AppColors.primaryElement,
            ),
          ),
        ),
      ),
    );
  }
}
