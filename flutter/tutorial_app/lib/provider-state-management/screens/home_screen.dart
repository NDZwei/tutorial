import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tutorial_app/provider-state-management/providers/movie_provider.dart';
import 'package:tutorial_app/provider-state-management/screens/favorite_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    var movies = context.watch<MovieProvider>().movies;
    var favorites = context.watch<MovieProvider>().favorites;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Provider state management', style: TextStyle(fontSize: 28),),
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            TextButton.icon(
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(builder: (context) => const FavoriteScreen()));
              },
              icon: const Icon(Icons.shopping_cart, size: 36),
              label: Text("(${favorites.length})", style: const TextStyle(fontSize: 24)),
              style: TextButton.styleFrom(
                padding: EdgeInsets.zero,
                alignment: Alignment.centerRight,
              ),
            ),

            Expanded(
                child: ListView.builder(
                    itemCount: movies.length,
                    itemBuilder: (_, index) {
                      final currentMovie = movies[index];
                      return Card(
                        key: ValueKey(currentMovie.title),
                        color: Colors.blueAccent,
                        elevation: 3,
                        child: ListTile(
                          title: Text(currentMovie.title,
                              style: const TextStyle(
                                  color: Colors.white, fontSize: 24)),
                          subtitle: Text(
                              currentMovie.duration ?? 'No information',
                              style: const TextStyle(
                                  color: Colors.white, fontSize: 18)),
                          trailing: IconButton(
                            icon: Icon(Icons.favorite,
                                color: favorites.contains(currentMovie)
                                    ? Colors.red
                                    : Colors.white,
                                size: 30),
                            onPressed: () {
                              if (!favorites.contains(currentMovie)) {
                                context
                                    .read<MovieProvider>()
                                    .addToFavorite(currentMovie);
                              } else {
                                context
                                    .read<MovieProvider>()
                                    .removeFromFavorite(currentMovie);
                              }
                            },
                          ),
                        ),
                      );
                    }))
          ],
        ),
      ),
    );
  }
}
