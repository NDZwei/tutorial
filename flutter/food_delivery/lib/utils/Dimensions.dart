
import 'package:get/get.dart';

class Dimensions {
  // height: 816, width: 432
  static double screenHeight = Get.context!.height;
  static double screenWidth = Get.context!.width;

  static double pageViewContainer = screenHeight/2.55; // 816/320
  static double pageViewTextContainer = screenHeight/3.71; // 816/220
  static double pageViewAlignContainer = screenHeight/5.828; // 816/220
}