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
        TrackId : Integer (PK)
        TrackName : String
        AlbumId : Integer (FK)
        Popularity: Integer
        DurationMs : decimal
        Explicit : boolean
        Danceability : decimal
        Energy : decimal
        Key : Integer
        Loudness : decimal
        Mode : Integer
        Speechiness : decimal
        Acousticness : decimal
        Instrumentalness : decimal
        Valence : decimal
        Tempo : decimal
        TimeSignature : Integer
        TrackGenre : Integer
    }
    class Album{
        AlbumId : Integer(PK)
        AlbumName : String
        ArtistId : Integer (FK)
    }
    class Artists{
        ArtistId : Integer (PK)
        ArtistName : String
    }
    class User{
        UserId : String
        Username : String
        Email : String
        PasswordHash : String
        DateJoin : timestamp
        LastLogin: timestamp
    }
    class Genres{
        GenreId : Integer (PK)
        GenreName : String
    }
    class Track_Genres{
        TrackId : Integer (FK)
        GenreId : Integer (FK)
    }
    class Moods{
        MoodId : Integer (PK)
        MoodName : String
        Emoji : String
    }
    class Track_Moods{
        TrackId : Integer (FK)
        MoodId : Integer (FK)
        UserId : String (FK)
        TagDate : timestamp
    }
    class Playlists{
        PlaylistId : Integer(PK)
        UserId : String (FK)
        Name : String
        Description : String
        CreationDate : datetime
        IsPublic : boolean
    }
    class Playlist_Tracks{
        RecoridId : Integer(PK)
        PlaylistId : Integer(FK)
        TrackId : Integer (FK)
        Position : Integer
    }
    class Comment{
        CommentId : Integer (PK)
        UserId : Integer (FK)
        ContentType : enum(TRACK, PLAYLIST)
        ContentId : Integer(FK)
        CommentText : String
        CommentTime : timestamp
    }
    class User_Preference{
        UserId : String (FK)
        PreferenceType : String
        PreferenceId : Integer
        Weight
    }
    class Listening_History{
        HistoryId : Integer (PK)
        UserId : String (FK)
        TrackId : Integer (FK)
        LastListenedTime : timestamp
        DurationListened : decimal
    }
    class User_Track_Interaction{
        TserId : String (FK)
        TrackId : Integer(FK)
        InteractionType : enum(LIKE, DISLIKE, SKIP)
        InteractionTime : timestamp
    }
    class Monthly_Track_Popularity{
        TrackId : Integer (FK)
        YearMonth : String
        TotalPlays : Integer
        UniqueListeners : Integer
    }
    class Occasions{
        OccasionTd : Integer (PK)
        OccasionName : String
    }
    class Track_Occasions{
        TrackId : Integer (FK)
        OccasionId : Integer (FK)
    }



```
