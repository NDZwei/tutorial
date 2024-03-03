
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/common/utils/function_utils.dart';
import 'package:river_pod_course_app/common/utils/toast_message.dart';
import 'package:river_pod_course_app/pages/sign_up/notifier/register_notifier.dart';

class SignUpController {
  late WidgetRef ref;

  SignUpController({required this.ref});

  void handleSignUp() {
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
  }
}