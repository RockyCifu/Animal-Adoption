import React from "react"
import { Link } from "react-router-dom"

const NavBar = () => {
  return (
    <div className="title-bar">
      <div className="top-bar-left">
        <ul className="menu">
          <li>
            <Link to="/pets">Home</Link>
          </li>
          <li>
            <Link to="/adoptions/new">Surrender Your Pet</Link>
          </li>
          <li>
            <Link to="/pending_applications">Pending Applications</Link>
          </li>
        </ul>
      </div>
    </div>
  )
}

export default NavBar
