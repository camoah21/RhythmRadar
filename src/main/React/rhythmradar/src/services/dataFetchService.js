import React, {useState, useEffect } from 'react';

function ArtistInfo() {
    const [artistName, setArtistName] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/api/artists/name?artistId=SOME_ARTIST_ID')
        .then(response => response.json())
        .then(data => {
            setArtistName(data);
        })
    }, []); // The empty dependency array means this useEffect runs once when the component mounts

    return (
        <div>
            <h1>{artistName}</h1>
        </div>
    );
}

export default ArtistInfo;