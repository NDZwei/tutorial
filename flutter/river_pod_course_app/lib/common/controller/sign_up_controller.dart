
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/common/app_loader/app_loader.dart';
import 'package:river_pod_course_app/common/utils/function_utils.dart';
import 'package:river_pod_course_app/common/utils/toast_message.dart';
import 'package:river_pod_course_app/pages/sign_up/notifier/register_notifier.dart';

class SignUpController {
  late WidgetRef ref;

  SignUpController({required this.ref});

  Future<void> handleSignUp() async {
    var state = ref.read(registerNotifierProvider);

    String username = state.username;
    String email = state.email;
    String password = state.password;
    String confirmPassword = state.confirmPassword;

    if(state.username.isEmpty || username.isEmpty) {
      toastInfo("Username is empty");
      return;
    }
    if(state.email.isEmpty || email.isEmpty) {
      toastInfo("Username is empty");
      return;
    }
    if(!FunctionUtils().isEmailValid(state.email)) {
      toastInfo("Email not valid");
      return;
    }
    if(state.password.isEmpty || password.isEmpty) {
      toastInfo("Password is empty");
      return;
    }
    if(state.confirmPassword.isEmpty || confirmPassword.isEmpty) {
      toastInfo("Confirm password is empty");
      return;
    }
    if(state.password != state.confirmPassword || password != confirmPassword) {
      toastInfo("Password did not match");
      return;
    }

    // show loading
    ref.read(appLoaderProvider.notifier).setLoaderValue(true);
    var context = Navigator.of(ref.context);
    try {
      final credential = await FirebaseAuth.instance.
              createUserWithEmailAndPassword(email: email, password: password);
      if(kDebugMode) {
        print(credential);
      }
      if(credential.user != null) {
        await credential.user?.sendEmailVerification();
        await credential.user?.updateDisplayName(username);
        toastInfo("Sending email confirmation. Check your email for more information");
        context.pop();
      }
    } on FirebaseAuthException catch (e) {
      if(e.code == 'weak-password') {
        toastInfo("This is a weak password");
      } else if(e.code == 'email-already-in-use') {
        toastInfo("This email address is already in use");
      } else if(e.code == 'user-not-found') {
        toastInfo("User not found");
      }
    } catch (e) {
      if(kDebugMode) {
        print(e.toString());
      }
    }
    ref.read(appLoaderProvider.notifier).setLoaderValue(false);
  }
}