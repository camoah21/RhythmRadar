-- Create database
CREATE DATABASE RhythmRadar;
USE RhythmRadar;

-- Artists table
CREATE TABLE Artists (
    artistId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    country VARCHAR(100)
);

-- Songs table
CREATE TABLE Songs (
    songId INT AUTO_INCREMENT PRIMARY KEY,
    artistId INT,
    title VARCHAR(255) NOT NULL,
    releaseDate DATE,
    duration INT,  -- Duration in seconds
    FOREIGN KEY (artistId) REFERENCES Artists(artistId)
);

-- SocialMedia table
CREATE TABLE SocialMedia (
    artistId INT PRIMARY KEY,
    instagramFollowers INT,
    twitterFollowers INT,
    youtubeSubscribers INT,
    FOREIGN KEY (artistId) REFERENCES Artists(artistId)
);

-- StreamingData table
CREATE TABLE StreamingData (
    songId INT PRIMARY KEY,
    spotifyStreams INT,
    appleMusicStreams INT,
    youtubeViews INT,
    FOREIGN KEY (songId) REFERENCES Songs(songId)
);

-- Albums table (Optional)
CREATE TABLE Albums (
    albumId INT AUTO_INCREMENT PRIMARY KEY,
    artistId INT,
    title VARCHAR(255) NOT NULL,
    releaseDate DATE,
    FOREIGN KEY (artistId) REFERENCES Artists(artistId)
);

-- AlbumSongs table (Optional)
CREATE TABLE AlbumSongs (
    albumId INT,
    songId INT,
    PRIMARY KEY (albumId, songId),
    FOREIGN KEY (albumId) REFERENCES Albums(albumId),
    FOREIGN KEY (songId) REFERENCES Songs(songId)
);

CREATE TABLE Users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    passwordHash VARCHAR(255) NOT NULL,
    dateRegistered TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

