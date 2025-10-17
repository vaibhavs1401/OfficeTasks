/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

(function () {
  const BANNER_ID = "cookie-banner";
  const COOKIE_NAME = "cookieConsent";
  const SESSION_COOKIE = "sessionId";
  const COOKIE_MAX_AGE = 60 * 60 * 24 * 365; // 1 year

  function getCookie(name) {
    const v = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
    return v ? v.pop() : null;
  }

  function setCookie(name, value, maxAgeSec) {
    // set secure options; adjust domain/path as required
    const secure = location.protocol === 'https:' ? ';Secure' : '';
    document.cookie = name + "=" + encodeURIComponent(value) + ";path=/;max-age=" + maxAgeSec + secure + ";SameSite=Lax";
  }

  function ensureSessionId() {
    let sid = getCookie(SESSION_COOKIE);
    if (!sid) {
      sid = "sess-" + Math.random().toString(36).substring(2, 15);
      setCookie(SESSION_COOKIE, sid, COOKIE_MAX_AGE);
    }
    return sid;
  }

  function showBanner() {
    const banner = document.getElementById(BANNER_ID);
    if (!banner) return;
    banner.style.display = 'block';
  }

  function hideBanner() {
    const banner = document.getElementById(BANNER_ID);
    if (banner) banner.style.display = 'none';
  }

  function postConsentToServer(payload) {
    fetch(contextPath + '/api/consent/save', {
      method: 'POST',
      credentials: 'same-origin', // include cookies
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    }).catch(err => console.error('Consent save failed', err));
  }

  document.addEventListener('DOMContentLoaded', function () {
    const localConsent = getCookie(COOKIE_NAME);
    const sessionId = ensureSessionId();

    // If a server-side rendered attribute exists telling consent was accepted,
    // the interceptor already set it and analytics were included server-side.
    // But we still check cookie for client-side behavior.
    if (!localConsent || localConsent !== 'accepted') {
      // show banner if not accepted
      showBanner();
    }

    // Accept all button
    const acceptBtn = document.getElementById('cookie-accept-all');
    if (acceptBtn) {
      acceptBtn.addEventListener('click', function () {
        const payload = {
          sessionId: sessionId,
          consentGiven: true,
          consentCategories: "necessary,analytics,marketing",
          cookieDetails: { necessary: true, analytics: true, marketing: true },
          consentVersion: "v1.0",
          consentSource: "web"
        };

        setCookie(COOKIE_NAME, 'accepted', COOKIE_MAX_AGE);
        postConsentToServer(payload);

        // Optional: dynamically enable analytics scripts if they weren't server-rendered
        // e.g. load Google Analytics here if needed.
        hideBanner();
      });
    }

    // Manage preferences (simple demonstration — open a modal / redirect to settings)
    const manageBtn = document.getElementById('cookie-manage');
    if (manageBtn) {
      manageBtn.addEventListener('click', function () {
        // show a modal or redirect to cookie settings page where user can toggle categories
        window.location.href = '/cookie-settings';
      });
    }
  });
})();


