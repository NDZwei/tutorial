
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tutorial_app/provider-state-management/providers/movie_provider.dart';

class FavoriteScreen extends StatefulWidget {
  const FavoriteScreen({super.key});

  @override
  State<FavoriteScreen> createState() => _FavoriteScreenState();
}

class _FavoriteScreenState extends State<FavoriteScreen> {
  @override
  Widget build(BuildContext context) {
    final _favorites = context
        .watch<MovieProvider>()
        .favorites;
    return Scaffold(
      appBar: AppBar(
        title: const Text('My favorite'),
      ),
      body: ListView.builder(
          itemCount: _favorites.length,
          itemBuilder: (_, index) {
            final currentMovie = _favorites[index];
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
                  icon: const Icon(Icons.delete_rounded, size: 30, color: Colors.white,),
                  onPressed: () {
                    context.read<MovieProvider>().removeFromFavorite(currentMovie);
                  },
                ),

              ),
            );
          }
      ),
    );
  }
}
