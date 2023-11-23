import React from "react";
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { TbBuildingStore } from 'react-icons/tb';
import { RiAccountPinCircleLine } from "react-icons/ri";
import { PiHandbagSimpleBold } from "react-icons/pi";

import "../styles/Navbar.css";

function Navbar() {
    const products = useSelector(state => state.navbarReducer.value);

    function numberOfProducts() {
        let number = 0;
        for (let i = 0; i < products.length; i++) {
            number += products[i].quantity;
        }
        return number;
    }

    const navigate = useNavigate();

    function handleClickIcon() {
        navigate("/");
        window.scroll({ top: 0, behavior: 'smooth' });
    }

    function handleClickHandBag() {
        navigate("/ShoppingCart");
        window.scroll({ top: 0, behavior: 'smooth' });
    }

    function handleProfileClick() {
        navigate("/ProfilePage");
        window.scroll({ top: 0, behavior: 'smooth' });
    }

    return (
        <div id="navbar-container">
            <div id="icon">
                <TbBuildingStore id="icon-in-div" onClick={handleClickIcon} />
            </div>
            <RiAccountPinCircleLine id="account-button" onClick={handleProfileClick}/>
            <PiHandbagSimpleBold id="hand-bag" onClick={handleClickHandBag} />
            <div id="number-of-products">{numberOfProducts()}</div>
        </div>
    );
}

export default Navbar;
