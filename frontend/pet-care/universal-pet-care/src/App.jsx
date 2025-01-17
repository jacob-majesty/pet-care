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

function App() {
  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path='/' element={<RootLayout />}>
        <Route index element={<Home />} />
        <Route path='/doctors' element={<VeterinarianListing />} />
        <Route
          path='/book-appointment/:recipientId/new-appointment'
          element={<BookAppointment />}
        />
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
