import api from '../utils/api';

export const fetchArtistDetails = artistId => dispatch => {
    return api.get(`/artist/${artistId}`)
        .then(response => {
            dispatch({ type: 'FETCH_ARTIST_SUCCESS', payload: response.data });
        })
        .catch(error => {
            // handle the error, maybe dispatch another action to set an error state
        });
};
