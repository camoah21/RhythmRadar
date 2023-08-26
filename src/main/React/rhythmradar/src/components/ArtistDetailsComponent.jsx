import React, { useEffect, useState } from 'react';
import api from '../utils/api';

const ArtistDetailsComponent = ({ artistId }) => {
    const [artistDetails, setArtistDetails] = useState(null);

    useEffect(() => {
        api.get(`/artist/${artistId}`).then(response => {
            setArtistDetails(response.data);
        });
    }, [artistId]);

    // Render logic here...
}

export default ArtistDetailsComponent;
