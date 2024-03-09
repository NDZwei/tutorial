
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/common/app_loader/app_loader.dart';
import 'package:river_pod_course_app/common/entity/user.dart';
import 'package:river_pod_course_app/pages/sign_in/notifier/sign_in_notifier.dart';

import '../utils/toast_message.dart';

class SignInController {
  WidgetRef ref;
  SignInController(this.ref);

  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  Future<void> handleSignIn() async {
    var state = ref.read(signInNotifierProvider);
    String email = state.email;
    String password = state.password;

    emailController.text = email;
    passwordController.text = password;

    if(state.email.isEmpty || email.isEmpty) {
      toastInfo("Email is empty");
      return;
    }

    if(state.password.isEmpty || password.isEmpty) {
      toastInfo("Password is empty");
      return;
    }

    ref.read(appLoaderProvider.notifier).setLoaderValue(true);
    try {
      final credential = await FirebaseAuth.instance
          .signInWithEmailAndPassword(email: email, password: password);
      if(credential.user == null) {
        toastInfo("User not found");
      }
      if(!credential.user!.emailVerified) {
        toastInfo("You must verify your email");
      }
      var user = credential.user;
      if(user != null) {
        String? id = user.uid;
        String? displayName = user.displayName;
        String? email = user.email;
        String? photoUrl = user.photoURL;

        LoginRequestEntity loginRequestEntity = LoginRequestEntity();
        loginRequestEntity.avatar = photoUrl;
        loginRequestEntity.name = displayName;
        loginRequestEntity.email = email;
        loginRequestEntity.open_id = id;
        loginRequestEntity.type = 1;
        asyncPostAllData(loginRequestEntity);
        print("Login successful");
      } else {
        toastInfo("Login error");
      }
    } on FirebaseAuthException catch(e) {
      if(e.code == 'user-not-found') {
        toastInfo("User not found");
      } else if(e.code == 'wrong-password') {
        toastInfo("Your password is wrong");
      }
      print(e);
    } catch (e) {
      if(kDebugMode) {
        print(e.toString());
      }
    }
    ref.read(appLoaderProvider.notifier).setLoaderValue(false);
  }
  void asyncPostAllData(LoginRequestEntity loginRequestEntity) {
    ref.read(appLoaderProvider.notifier).setLoaderValue(true);
    
    ref.read(appLoaderProvider.notifier).setLoaderValue(false);

  }
}