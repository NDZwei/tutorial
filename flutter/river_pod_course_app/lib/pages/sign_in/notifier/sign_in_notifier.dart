
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/pages/sign_in/notifier/sign_in_state.dart';

class SignInNotifier extends StateNotifier<SignInState> {
  SignInNotifier():super(const SignInState());

  void onEmailChange(String email) {
    state = state.copyWith(email: email);
  }

  void onPasswordChange(String password) {
    state = state.copyWith(password: password);
  }
}

final signInNotifierProvider = StateNotifierProvider<SignInNotifier, SignInState>
                              ((ref) => SignInNotifier());