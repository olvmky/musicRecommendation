# Music Recommendation
SoundWave is a personalized music platform that offers mood-based playlists, dynamic music discovery, and real-time social interaction, catering to music lovers seeking more tailored recommendations, interactive features, and evolving playlists beyond the limitations of traditional services.
 
Features we plan to deliver this semester
1. ***Mood-Based Tagging***: Users can tag songs and playlists with emojis to represent the mood or vibe of the music (e.g., happy, sad, energetic). This data will later be used to filter songs and improve recommendations, allowing users to find music that matches their emotional state quickly.
2. ***Social Interaction***: Add a comment section for each song and playlist where users can engage in discussions, share opinions, and recommend similar tracks to others. This feature fosters community-building and deeper user engagement.
3. ***Dynamic Music Search***: Implement a real-time search feature that adapts based on user input. Users can filter songs by mood, genre, artist, occasions, or other attributes. The search results update dynamically as users refine their preferences, ensuring they find the perfect tracks tailored to both their personal taste and specific events or activities.
4. ***Personalized Music List Recommendations (Advanced Feature)***: Utilize user preferences, listening history, and behavior to generate personalized music recommendations. Over time, the platform will refine suggestions based on user feedback, such as favoring certain songs, avoiding specific genres, or skipping explicit content. Additionally, the system will offer alternative or similar music suggestions to enhance discovery.
5. ***Monthly Music Popularity (Advanced Feature)***: Analyze the popularity of tracks each month, using an algorithm that scores songs from 0 to 100 based on total and recent plays. This feature highlights the most popular songs of the moment and curates holiday-specific recommendations based on seasonal trends. Users can explore tracks that are trending now or revisit top songs from past months to create perfect playlists for upcoming holidays or special occasions.

```mermaid
classDiagram
    
    Track o-- Album
    Track o-- Track_Genres
    Track o-- Track_Moods
    Track o-- Playlist_Tracks
    Track o-- Comment
    Track o-- Listening_History
    Track o-- User_Track_Interaction
    Track o-- Monthly_Track_Popularity
    Track o-- Track_Occasions
    Album o-- Artists
    Genres o-- Track_Genres
    Moods o-- Track_Moods
    User o-- Track_Moods
    User o-- Playlists
    User o-- Comment
    User "1" o-- "*" User_Preference
    User o-- Listening_History
    User o-- User_Track_Interaction
    Occasions o-- Track_Occasions
    Playlists o-- Playlist_Tracks
    Playlists o-- Comment

    
    class Track{
        track_id : Integer (PK)
        track_name : String
        album_id : Integer (FK)
        popularity: String
        duration_ms : decimal
        explicit : boolean
        danceability : decimal
        energy : decimal
        key : Integer
        loudness : decimal
        mode : Integer
        speechiness : decimal
        acousticness : decimal
        instrumentalness : decimal
        valence : decimal
        tempo : decimal
        time_signature : Integer
        track_genre : Integer
    }
    class Album{
        album_id : Integer(PK)
        album_name : String
        artist_id : Integer (FK)
    }
    class Artists{
        artist_id : Integer (PK)
        artist_name : String
    }
    class User{
        user_id : String
        username : String
        email : String
        password_hash : String
        date_join : timestamp
        last_login: timestamp
    }
    class Genres{
        genre_id : Integer (PK)
        genre_name : String
    }
    class Track_Genres{
        track_id : Integer (FK)
        genre_id : Integer (FK)
    }
    class Moods{
        mood_id : Integer (PK)
        mood_name : String
        emoji : String
    }
    class Track_Moods{
        track_id : Integer (FK)
        mood_id : Integer (FK)
        user_id : String (FK)
        tag_date : timestamp
    }
    class Playlists{
        playlist_id : Integer(PK)
        user_id : String (FK)
        name : String
        description : String
        creation_date : datetime
        is_public : boolean
    }
    class Playlist_Tracks{
        recorid_id : Integer(PK)
        playlist_id : Integer(FK)
        track_id : Integer (FK)
        position : Integer
    }
    class Comment{
        comment_id : Integer (PK)
        user_id : Integer (FK)
        content_type : enum(TRACK, PLAYLIST)
        content_id : Integer(FK)
        comment_text : String
        comment_time : timestamp
    }
    class User_Preference{
        user_id : String (FK)
        preference_type : String
        preference_id : Integer
        weight
    }
    class Listening_History{
        history_id : Integer (PK)
        user_id : String (FK)
        track_id : Integer (FK)
        last_listened_time : timestamp
        duration_listened : decimal
    }
    class User_Track_Interaction{
        user_id : String (FK)
        track_id : Integer(FK)
        interaction_type : enum(LIKE, DISLIKE, SKIP)
        interaction_time : timestamp
    }
    class Monthly_Track_Popularity{
        track_id : Integer (FK)
        year_month : String
        total_plays : Integer
        unique_listeners : Integer
    }
    class Occasions{
        occasion_id : Integer (PK)
        occasion_name : String
    }
    class Track_Occasions{
        track_id : Integer (FK)
        occasion_id : Integer (FK)
    }


```
