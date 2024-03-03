import 'package:river_pod_course_app/pages/sign_up/notifier/register_state.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
part 'register_notifier.g.dart';

@riverpod
class RegisterNotifier extends _$RegisterNotifier {
  @override
  RegisterState build() {
    return RegisterState();
  }

  void onUsernameChange(String username) {
    state = state.copyWith(username: username);
  }

  void onEmailChange(String email) {
    state = state.copyWith(email: email);
  }

  void onPasswordChange(String password) {
    state = state.copyWith(password: password);
  }

  void onConfirmPasswordChange(String confirmPassword) {
    state = state.copyWith(confirmPassword: confirmPassword);
  }
}
