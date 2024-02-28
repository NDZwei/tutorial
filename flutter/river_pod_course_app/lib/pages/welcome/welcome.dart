
import 'package:dots_indicator/dots_indicator.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:river_pod_course_app/common/utils/app_colors.dart';

import '../../common/widgets/widget_custom.dart';
import 'notifier/welcome_notifier.dart';

class Welcome extends ConsumerWidget {
  Welcome({super.key});

  final PageController _controller = PageController();

  @override
  Widget build(BuildContext context, WidgetRef ref) {

    final index = ref.watch(indexDotProvider);

    return SafeArea(
      child: Scaffold(
        backgroundColor: Colors.white,
        body: Container(
          margin: const EdgeInsets.only(top: 30),
          child: Stack(
            alignment: Alignment.topCenter,
            children: [
              PageView(
                onPageChanged: (value) {
                  ref.read(indexDotProvider.notifier).changeIndex(value);
                },
                controller: _controller,
                scrollDirection: Axis.horizontal,
                children: [
                  appOnboardingPage(
                      index: 1,
                      controller: _controller,
                      imagePath: "assets/images/reading.png",
                      title: "First see learning",
                      subtitle: "For get about of paper all knowledge in one learning"
                  ),
                  appOnboardingPage(
                      index: 2,
                      controller: _controller,
                      imagePath: "assets/images/boy.png",
                      title: "Connect with everyone",
                      subtitle: "Always keep in touch with your tutor and friends. Let's get connected"
                  ),
                  appOnboardingPage(
                      index: 3,
                      controller: _controller,
                      imagePath: "assets/images/man.png",
                      title: "Always faceted learning",
                      subtitle: "Anywhere, anytime. The time is at your discretion. So study wherever you can"
                  ),
                ],
              ),
              Positioned(
                bottom: 170,
                child: DotsIndicator(
                  position: index,
                  dotsCount: 3,
                  mainAxisAlignment: MainAxisAlignment.center,
                  decorator: DotsDecorator(
                    activeColor: AppColors.primaryElement,
                    size: const Size.square(9),
                    activeSize: const Size(24, 8),
                    activeShape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(5)
                    )
                  ),
                )
              )
            ],
          ),
        )
      ),
    );
  }
}
