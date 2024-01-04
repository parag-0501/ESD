import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { add } from "../redux/features/navbar/navbarSlice";

import "../styles/SingleProduct.css";

function SingleProduct(props) {
    const [review, setReview] = useState("");
    const dispatch = useDispatch();

    const handleReviewSubmit = (event) => {
        event.preventDefault();
        console.log("Submitted review:", review);
        setReview("");
    };

    return (
        <div id="single-product-container">
            <div className="flex-item">
                <img src={props.productDetails.thumbnail} alt="product image" />
            </div>

            <div id="details" className="flex-item">
                {/* Product details */}
                <h2 id="brand">{props.productDetails.brand}</h2>
                <h2 id="title">{props.productDetails.title}</h2>
                <h2 id="description">"{props.productDetails.description}"</h2>
                <span>Category: {props.productDetails.category.toUpperCase()}</span>
                <div id="price-container">
                    <h2 id="price">
                        <span>₹</span>
                        {props.productDetails.price * 83}
                    </h2>
                </div>
                <button onClick={() => dispatch(add(props.productDetails))}>
                    Add to cart
                </button>
            </div>

            <div id="review-section" className="review-section flex-item">
                {/* Review section */}
                <h2>Add a Review</h2>
                <form onSubmit={handleReviewSubmit}>
                    <textarea
                        value={review}
                        onChange={(e) => setReview(e.target.value)}
                        placeholder="Write your review..."
                    />
                    <button type="submit">Submit Review</button>
                </form>
            </div>
        </div>
    );
}

export default SingleProduct;
