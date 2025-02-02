import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./components/home/Home";
import VeterinarianListing from "./components/veterinarian/VeterinarianListing";
import {
  Route,
  Router,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";
import RootLayout from "./components/layout/RootLayout";
import BookAppointment from "./components/appointment/BookAppointment";
import Veterinarian from "./components/veterinarian/Veterinarian";
import UserRegistration from "./components/user/UserRegistration";
import Login from "./components/auth/Login";
import UserDashboard from "./components/user/UserDashboard";
import UserUpdate from "./components/user/UserUpdate";
import AdminDashboard from "./components/admin/AdminDashboard";
import EmailVerification from "./components/auth/EmailVerification";
import ProtectedRoute from "./components/auth/ProtectedRoute";
import PasswordResetRequest from "./components/auth/PasswordResetRequest";
import ResetPassword from "./components/auth/ResetPassword";

function App() {
  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path='/' element={<RootLayout />}>
        {/* Routes accessible without authentication */}

        <Route index element={<Home />} />
        <Route path='/doctors' element={<VeterinarianListing />} />

        <Route
          path='/veterinarian/:vetId/veterinarian'
          element={<Veterinarian />}
        />

        <Route path='/register-user' element={<UserRegistration />} />

        <Route path='/login' element={<Login />} />

        <Route
          path='/vet-reviews/:vetId/veterinarian'
          element={<Veterinarian />}
        />

        <Route
          path='/password-rest-request'
          element={<PasswordResetRequest />}
        />

        <Route path='/reset-password' element={<ResetPassword />} />

        <Route path='/email-verification' element={<EmailVerification />} />
        {/* Routes accessible without authentication */}

        {/* Wrap the routes that require authentication and possibly authorization */}
        <Route
          element={
            <ProtectedRoute
              allowedRoles={["ROLE_PATIENT", "ROLE_ADMIN", "ROLE_VET"]}
              useOutlet={true}
            />
          }>
          <Route
            path='/user-dashboard/:userId/my-dashboard'
            element={<UserDashboard />}
          />

          <Route
            path='/book-appointment/:recipientId/new-appointment'
            element={<BookAppointment />}
          />

          <Route path='/update-user/:userId/update' element={<UserUpdate />} />
        </Route>

        {/************  End authenticated users only  *******************/}

        {/* Admin-only routes */}
        <Route
          element={
            <ProtectedRoute allowedRoles={["ROLE_ADMIN"]} useOutlet={true} />
          }>
          <Route
            path='/admin-dashboard/:userId/admin-dashboard'
            element={<AdminDashboard />}
          />
        </Route>
      </Route>
    )
  );
  return (
    <main className=''>
      <RouterProvider router={router} />
    </main>
  );
}

export default App;
