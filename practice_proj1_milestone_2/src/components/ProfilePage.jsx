import React, { useState } from 'react';
import '../styles/ProfilePage.css'; // Import CSS file for styling (replace with your actual CSS file)

function ProfilePage() {
  // Mock user data (you might fetch this from an API)
  const [user, setUser] = useState({
    name: "Parag Dutt Sharma",
    email: "parag@gmail.com",
    address: "IIIT Bangalore",
    profilePic: "https://media.licdn.com/dms/image/D4D03AQG2dE2Q_dhQtw/profile-displayphoto-shrink_200_200/0/1689252923270?e=1706140800&v=beta&t=fY9c4Ydefqi8qzgkLmg-boYBooGjvoa1W0P2KW4ImNc",
  });

  const [editMode, setEditMode] = useState(false);

  const handleInputChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const handleEditProfile = () => {
    setEditMode(true);
  };

  const handleSaveProfile = () => {
    setEditMode(false);
    // Here you might send updated user data to the server
    console.log("Updated User Data:", user);
  };

  return (
    <div className="profile-container">
      <h1>User Profile</h1>
      
      <div className="profile-details">

        <img src={user.profilePic} alt="Profile_Pic" />
        
        <div className='profile-label'>
          <div>
            <label>Name:</label>
            {editMode ? (
              <input
                type="text"
                name="name"
                value={user.name}
                onChange={handleInputChange}
              />
            ) : (
              <span>{user.name}</span>
            )}
          </div>
          
          <div>
            <label>Email:</label>
            {editMode ? (
              <input
                type="email"
                name="email"
                value={user.email}
                onChange={handleInputChange}
              />
            ) : (
              <span>{user.email}</span>
            )}
          </div>
        
          <div>
            <label>Address:</label>
            {editMode ? (
              <input
                type="text"
                name="address"
                value={user.address}
                onChange={handleInputChange}
              />
            ) : (
              <span>{user.address}</span>
            )}
          </div>
        </div>
        
      </div>

      <div className="profile-actions">
        {editMode ? (
          <button onClick={handleSaveProfile}>Save</button>
        ) : (
          <button onClick={handleEditProfile}>Edit</button>
        )}
      </div>
      
    </div>
  );
};

export default ProfilePage;
