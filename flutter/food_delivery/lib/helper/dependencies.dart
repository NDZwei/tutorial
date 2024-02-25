import 'package:food_delivery/data/controller/api_client.dart';
import 'package:food_delivery/data/controller/popular_product_controller.dart';
import 'package:food_delivery/data/repository/popular_product_repository.dart';
import 'package:get/get.dart';

Future<void> init() async {
  
  Get.lazyPut(() => ApiClient(appBaseUrl: "https://www.dbestech.com"));
  
  Get.lazyPut(() => PopularProductRepository(apiClient: Get.find()));
  
  Get.lazyPut(() => PopularProductController(popularProductRepository: Get.find()));
}