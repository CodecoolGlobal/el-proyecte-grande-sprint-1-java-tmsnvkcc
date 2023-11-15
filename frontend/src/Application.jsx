import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from 'react-router-dom';
import {
  ErrorPage,
  Home, Track, Dashboard, Profile,
} from 'pages/';
import { Layout } from 'components/layout';
import './index.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCircleExclamation, faCircleNotch } from '@fortawesome/free-solid-svg-icons';
library.add(faCircleExclamation, faCircleNotch);

const router = createBrowserRouter(createRoutesFromElements(
  <Route errorElement={<ErrorPage />}>
    <Route path={'/'}>
      <Route index element={<Home />} />
    </Route>
    <Route path={'/profile'} element={<Layout />}>
      <Route index element={<Profile />} />
    </Route>
    <Route path={'/track'} element={<Layout />}>
      <Route index element={<Track />}/>
    </Route>
    <Route path={'/dashboard'} element={<Layout />}>
      <Route index element={<Dashboard />}/>
    </Route>
  </Route>,
));

const Application = () => {
  return (
    <RouterProvider router={router} />
  );
};

export default Application;
