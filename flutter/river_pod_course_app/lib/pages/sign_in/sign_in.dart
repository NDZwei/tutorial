import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/common/app_loader/app_loader.dart';
import 'package:river_pod_course_app/common/controller/sign_in_controller.dart';
import 'package:river_pod_course_app/common/widgets/button_custom.dart';
import 'package:river_pod_course_app/common/widgets/sign_in_widgets.dart';
import 'package:river_pod_course_app/common/widgets/text_custom.dart';
import 'package:river_pod_course_app/pages/sign_in/notifier/sign_in_notifier.dart';

import '../../common/utils/app_colors.dart';

class SignIn extends ConsumerStatefulWidget {
  const SignIn({Key? key}) : super(key: key);

  @override
  ConsumerState<SignIn> createState() => _SignInState();
}

class _SignInState extends ConsumerState<SignIn> {
  late SignInController _controller;

  @override
  void initState() {
    _controller = SignInController(ref);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final signInProvider = ref.watch(signInNotifierProvider);
    final loader = ref.watch(appLoaderProvider);
    return Container(
      color: Colors.white,
      child: SafeArea(
        child: Scaffold(
          appBar: buildAppbar(text: "Login"),
          backgroundColor: Colors.white,
          body: loader == false ?
          SingleChildScrollView(
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
                    hintText: "Input your email address",
                    controller: _controller.emailController,
                    func: (value) => ref
                        .read(signInNotifierProvider.notifier)
                        .onEmailChange(value)
                ),
                const SizedBox(height: 20,),
                textFieldInput(
                    text: "Password",
                    icon: "assets/icons/lock.png",
                    hintText: "******",
                    isPassword: true,
                    controller: _controller.passwordController,
                    func: (value) => ref
                        .read(signInNotifierProvider.notifier)
                        .onPasswordChange(value)
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
                    isLogin: true,
                    func: () => _controller.handleSignIn(),
                  ),
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